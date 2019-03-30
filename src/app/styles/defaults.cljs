(ns app.styles.defaults)

(defn defaults
  [gs create-font-styles]
  {:html {:box-sizing :border-box}
   "*" {:box-sizing :inherit
        "&:before" {:box-sizing :inherit}
        "&:after" {:box-sizing :inherit}}

   "ol, ul" {:list-style :none
             :padding-left 0}})
