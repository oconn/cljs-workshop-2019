(ns app.router.core
  (:require [re-frame.core :as re-frame]
            [re-frame-routing.core :as rfr]

            [app.components.composite.loading :as loading]
            [app.containers.home :as home]
            [app.containers.not-found :as not-found]
            [app.components.base.text :as text]))

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Routing Utilities
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def route-middleware
  (rfr/create-route-middleware
   {:loading-view (fn [] [:<> [loading/render {:container-height "400px"}]])}))

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Route Middleware
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn ensure-resource
  [event-key dispatch-fn {:keys [route-params
                                 route-query
                                 middleware-state]
                          :as ctx}]
  (let [request (re-frame/subscribe [:request/track-request event-key])
        has-not-bootstrapped (-> middleware-state
                                 :bootstrapped-resources
                                 event-key
                                 nil?)
        ctx* (cond-> ctx
               (true? has-not-bootstrapped)
               (assoc-in [:middleware-state :bootstrapped-resources event-key]
                         true))]

    (when (true? has-not-bootstrapped)
      (dispatch-fn route-params))

    (if (true? (contains? #{:loading :never-requested} (:status @request)))
      (assoc ctx* :is-loading true)
      ctx*)))

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Container Views
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defmulti containers identity)

(defmethod containers
  :home [] [(route-middleware home/render
                              [])])

;; catch-all

(defmethod containers
  :default [] [(route-middleware not-found/render [])])

;; router

(defn router []
  (let [route (re-frame/subscribe [:router/route])]
    (fn []
      (set! (-> js/document .-body .-scrollTop) 0)
      (set! (-> js/document .-documentElement .-scrollTop) 0)

      [containers @route])))
