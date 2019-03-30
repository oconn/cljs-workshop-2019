(ns app.components.base.button
  (:require [devcards.core :as devcards]

            [app.styles.core :refer [gs
                                     styles->classes
                                     add-class
                                     create-font-styles]])
  (:require-macros [devcards.core :refer [defcard-rg]]))

(def base-button-styles
  (merge
   (create-font-styles {:style :caption-30
                        :color :primary-500
                        :family :primary})
   {:padding [(gs [:spacing :p8])
              (gs [:spacing :p20])]
    :white-space :nowrap
    :text-decoration :none
    :cursor :pointer
    :outline :none
    :position :relative
    :transition (str "background-color 0.2s ease, "
                     "color 0.2s ease, "
                     "border-color 0.2s ease")
    :border (str "2px solid " (gs [:colors :primary-500]))

    "&:disabled"
    {:background-color (gs [:colors :gray-scale-50])
     :color (gs [:colors :gray-scale-300])
     :border (gs [:borders :border-50-1])
     :cursor :default}}))

(def primary-button-styles
  (merge
   base-button-styles
   {:background-color (gs [:colors :primary-500])
    :color (gs [:colors :true-white])

    "&:hover"
    {:background-color (gs [:colors :primary-600])
     :border-color (gs [:colors :primary-600])}}))

(def secondary-button-styles
  (merge
   base-button-styles
   {:background-color (gs [:colors :true-white])

    "&:hover"
    {:background-color (gs [:colors :primary-500])
     :color (gs [:colors :true-white])}}))

(def classes
  (styles->classes
   {:primary-button primary-button-styles
    :secondary-button secondary-button-styles}))

(defn- button
  ([class-name button-content]
   [button class-name {} button-content])
  ([class-name options button-content]
   [:button (add-class options class-name classes) button-content]))

(def primary-button (partial button :primary-button))
(def secondary-button (partial button :secondary-button))

(defcard-rg primary-button
  "Primary button"
  [primary-button {} "Primary Button"])

(defcard-rg secondary-button
  "Secondary button"
  [secondary-button {} "Secondary Button"])
