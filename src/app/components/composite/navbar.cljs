(ns app.components.composite.navbar
  (:require [app.components.base.text :as text]
            [app.styles.core :refer [gs styles->classes add-class]]))

(def classes
  (styles->classes
   {:navbar-container
    {:padding (gs [:spacing :p20])
     :box-shadow (gs [:shadows :shadow-10])
     :margin-bottom (gs [:spacing :p20])}

    :link-container
    {:display :flex
     :justify-content :flex-end

     "& > li.link"
     {:margin-left (gs [:spacing :p20])

      "& > a"
      {:color (gs [:colors :link])
       :text-decoration :none}

      "&:first-child"
      {:margin-left (gs [:spacing :p0])}}}}))

(def routes
  [{:display "Home"
    :path "/"}])

(defn render-route
  [{:keys [display path]}]
  [:li {:class ["link"]}
   [text/body-30 :a {:href path}
    display]])

(defn render
  []
  [:nav (add-class {} :navbar-container classes)
   [:ul (add-class {} :link-container classes)
    (for [{:keys [path] :as route} routes]
      ^{:key path} [render-route route])]])
