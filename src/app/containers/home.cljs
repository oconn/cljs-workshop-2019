(ns app.containers.home
  (:require [re-frame.core :as re-frame]

            [app.components.base.text :as text]
            [app.components.base.page-container :as page-container]
            [app.styles.core :refer [styles->classes gs]]))

(def classes
  (styles->classes
   {}))

(defn render
  []
  [page-container/render
   {}
   [text/title-10 :h1 "Hello World"]])
