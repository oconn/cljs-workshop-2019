(ns app.events.core
  (:require [re-frame.core :as re-frame]
            [re-frame-notifier.core :as rf-notifier]
            [re-frame-request.core :as rf-request]
            [re-frame-routing.core :as rf-routing]

            [app.events.bootstrap]
            [app.events.utils :refer [app-interceptors]]
            [app.router.routes :as routes]))

(defn inc-counter
  [db [_ button-name]]
  (update-in db [:special-button button-name] inc))

(re-frame/reg-event-db :special-button/inc-counter inc-counter)

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Third Party Re-Frame Events
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(rf-notifier/register-events
 {:notifier-interceptors [app-interceptors]})

(rf-request/register-events
 {:request-interceptors [app-interceptors]})

(rf-routing/register-events
 {:set-route-interceptors [app-interceptors]
  :routes routes/routes})
