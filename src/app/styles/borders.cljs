(ns app.styles.borders)

(defn borders
  [colors]
  {:border-50-1 (str "1px solid " (:gray-scale-50 colors))
   :border-100-1 (str "1px solid " (:gray-scale-100 colors))})
