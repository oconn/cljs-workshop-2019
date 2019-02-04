(ns app.containers.not-found
  (:require [app.components.base.text :as text]
            [app.components.base.page-container :as page-container]))

(defn render
  []
  [page-container/render
   {}
   [text/display-20 :h1 "Not Found"]])
