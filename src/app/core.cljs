(ns app.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [re-frame-notifier.core :as re-frame-notifier]

            [app.components.composite.navbar :as navbar]
            [app.constants]
            [app.db.core]
            [app.events.core]
            [app.subscriptions.core]
            [app.router.core :as router]))

(defn core-container
  []
  [:<>
   [:<>
    [navbar/render]

    [router/router]]
   [re-frame-notifier/render-notifications]])

(defn mount-root
  "Application entry point"
  []
  (re-frame/dispatch [:bootstrap/start])

  (reagent/render [core-container]
                  (js/document.getElementById "main")))

(mount-root)
