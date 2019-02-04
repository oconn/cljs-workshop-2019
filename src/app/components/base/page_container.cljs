(ns app.components.base.page-container
  (:require [app.styles.core :refer [gs styles->classes add-class]]))

(def classes
  (styles->classes
   {:page-container
    {:padding [[(gs [:spacing :p0])
                (gs [:spacing :p20])
                (gs [:spacing :p0])
                (gs [:spacing :p20])]]}}))

(defn render
  [page-details & page-contents]
  [into
   [:div (add-class {} :page-container classes)]
   page-contents])
