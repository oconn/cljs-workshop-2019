(ns app.subscriptions.core
  (:require [re-frame-notifier.core :as rf-notifier]
            [re-frame-request.core :as rf-request]
            [re-frame-routing.core :as rf-routing]

            [re-frame.core :as re-frame]))

(defn button
  [db [_ button-name]]
  (or (get-in db [:special-button button-name]) 0))

(re-frame/reg-sub :special-button/button button)

(rf-notifier/register-subscriptions)
(rf-request/register-subscriptions)
(rf-routing/register-subscriptions)
