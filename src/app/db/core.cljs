(ns app.db.core
  (:require [cljs.spec.alpha :as s]
            [re-frame-notifier.core :as rf-notifier]
            [re-frame-request.core :as rf-request]
            [re-frame-routing.core :as rf-routing]

            ;; NOTE example use only, should live in app.db
            [app.components.composite.smart-button :as smart-button]))

(def default-db
  {:notifier rf-notifier/initial-state
   :request rf-request/initial-state
   :router rf-routing/initial-state
   :smart-button smart-button/initial-state})

(s/def :app.db.core/db
  (s/keys :req-un [:re-frame-notifier.core/notifier
                   :re-frame-request.core/request
                   :re-frame-routing.core/router
                   ::smart-button/smart-button]))
