(ns app.containers.home
  (:require [re-frame.core :as re-frame]

            [app.components.base.button :as button]
            [app.components.base.text :as text]
            [app.components.base.page-container :as page-container]
            [app.components.composite.smart-button :as smart-button]
            [app.styles.core :refer [styles->classes gs]]))

(def classes
  (styles->classes
   {}))

(defn render
  []
  [page-container/render
   {}
   [:div
    [text/title-20 :h4 "Base Button Components"]

    [button/primary-button
     {:style {:margin-right (gs [:spacing :p20])}}
     "Click me..."]
    [button/secondary-button "Click me..."]]

   [:div
    [text/title-20 :h4 "Composit Button Component Example"]

    [text/caption-20 :p "Counter state in re-frame"]
    [smart-button/render-smart-button-1]

    [text/caption-20 :p "Counter state in re-frame (backed by API / DB)"]
    [smart-button/render-smart-button-2]]])
