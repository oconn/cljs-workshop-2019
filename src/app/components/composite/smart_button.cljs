(ns app.components.composite.smart-button
  "Note: This ns is intended to demonstrate re-frame patterns and does not
              follow best practices for code organization."
  (:require [cljs.spec.alpha :as s]
            [cljs.test :refer-macros [deftest is testing]]
            [re-frame.core :as re-frame]

            [app.components.base.button :as button]
            [app.events.utils :refer [reg-event-db
                                      reg-event-fx]]))

;; DB (app.db.smart-button)

(def initial-state {:counter 0})

(s/def ::counter number?)
(s/def ::smart-button (s/keys :req-un [::counter]))

;; Subscriptions (app.subscriptions.smart-button)

(defn smart-button-sub
  [{:keys [smart-button]} _] smart-button)

(re-frame/reg-sub :smart-button/smart-button
                  smart-button-sub)

(defn counter-sub
  [{:keys [counter]} _] counter)

(re-frame/reg-sub :smart-button/counter
                  #(re-frame/subscribe [:smart-button/smart-button])
                  counter-sub)

;; Events (app.events.smart-button)

(defn inc-counter-success
  [{:keys [db]} [_ {:keys [counter]}]]
  {:db (assoc-in db [:smart-button :counter] counter)})

(reg-event-fx :smart-button/inc-counter-success
              inc-counter-success)

(defn inc-counter-failure
  [{:keys [db]} [_ err]]
  (js/console.warn "Handle Error!")
  {:db db})

(reg-event-fx :smart-button/inc-counter-failure
              inc-counter-failure)

(defn inc-counter
  [{:keys [db]} [event-name]]
  {:db db
   :request-mock
   {:name event-name
    :method :post
    :uri "https://www.api.com/inc-counter"
    :response-format :json
    :on-success [:smart-button/inc-counter-success]
    :on-failure [:smart-button/inc-counter-failure]
    :mock {:time 2000
           :data {:counter (-> db
                               (get-in [:smart-button :counter])
                               (inc))}

           ;; Note: Adding an error object will triger :on-failure
           ;; :error {}
           }}})

(reg-event-fx :smart-button/inc-counter inc-counter)

(defn inc-counter-directly
  [{:keys [db]} _]
  {:db (update-in db [:smart-button :counter] inc)})

(reg-event-fx :smart-button/inc-counter-directly inc-counter-directly)

(defn reset-counter
  [db _]
  (assoc-in db [:smart-button :counter] 0))

(reg-event-db :smart-button/reset-counter reset-counter)

(defn set-counter
  [db [_ value]]
  (assoc-in db [:smart-button :counter] value))

(reg-event-db :smart-button/set-counter set-counter)

;; View

(defn render-smart-button-1
  []
  (let [counter (re-frame/subscribe [:smart-button/counter])]
    (fn []
      [button/primary-button
       {:on-click #(re-frame/dispatch
                    [:smart-button/inc-counter-directly])}
       (str "Click count: " @counter)])))

(defn render-smart-button-2
  []
  (let [counter (re-frame/subscribe [:smart-button/counter])
        is-dispatching (re-frame/subscribe [:request/is-dispatching
                                            :smart-button/inc-counter])]
    (fn []
      [button/primary-button
       (cond-> {:on-click #(re-frame/dispatch
                            [:smart-button/inc-counter])}
         (true? @is-dispatching)
         (assoc :disabled true))
       (str "Click count: " @counter)])))

;; Tests (app.events.smart-button-test)

(def initial-context
  {:db {:smart-button {:counter 0}}})

(deftest inc-counter-directly-test
  (testing (str "inc-counter will increment counter state by one "
                "each time it is called")
    (let [first-call (inc-counter-directly initial-context nil)
          second-call (inc-counter-directly first-call nil)
          third-call (inc-counter-directly second-call nil)]
      (is (= 1 (get-in first-call [:db :smart-button :counter])))
      (is (= 2 (get-in second-call [:db :smart-button :counter])))
      (is (= 3 (get-in third-call [:db :smart-button :counter]))))))

(comment
  "Test out in your REPL!"

  (re-frame/dispatch [:smart-button/inc-counter-directly])
  (re-frame/dispatch [:smart-button/inc-counter])
  (re-frame/dispatch [:smart-button/reset-counter])
  (re-frame/dispatch [:smart-button/set-counter 1000])

  ;; Test out specs. The following will inject invalid state into the db
  (re-frame/dispatch [:smart-button/set-counter "cat"])

  @(re-frame/subscribe [:smart-button/counter])
  )
