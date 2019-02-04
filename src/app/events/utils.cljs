(ns app.events.utils
  (:require [re-frame.core :as re-frame]

            [app.events.interceptors.spec :refer [check-spec-interceptor]]))

(def app-interceptors
  [(when ^boolean js/goog.DEBUG check-spec-interceptor)
   #_(when ^boolean js/goog.DEBUG re-frame/debug)])

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Override re-frame event handlers
;;
;; https://github.com/Day8/re-frame/blob/master/docs/FAQs/GlobalInterceptors.md
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn reg-event-db
  "Wraps re-frame/reg-event-db to add global interceptors"
  ([id handler]
   (reg-event-db id nil handler))
  ([id interceptors handler]
   (re-frame/reg-event-db
    id
    (conj [app-interceptors] interceptors)
    handler)))

(defn reg-event-fx
  "Wraps re-frame/reg-event-fx to add global interceptors"
  ([id handler]
   (reg-event-fx id nil handler))
  ([id interceptors handler]
   (re-frame/reg-event-fx
    id
    (conj [app-interceptors] interceptors)
    handler)))
