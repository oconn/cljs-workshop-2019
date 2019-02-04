(ns app.styles.defaults)

(defn defaults
  [gs create-font-styles]
  {:html {:box-sizing :border-box}
   "*" {:box-sizing :inherit
        "&:before" {:box-sizing :inherit}
        "&:after" {:box-sizing :inherit}}

   "ol, ul" {:list-style :none
             :padding-left 0}

   ".no-print" {"@media print" {:display "none !important"}}

   ".print-only" {:display :none
                  "@media print" {:display :initial}}

   "@media print" {".page-break" {:display :block
                                  :position :relative
                                  :page-break-after :always}}

   ".markdown-container"
   {:max-width (gs [:constants :max-paragraph-width])
    :margin :auto

    "& *"
    {:font-family (gs [:font-family :open-sans])
     :color (gs [:colors :gray-scale-800])}

    "& h1" (create-font-styles :display-20 (gs [:colors :gray-scale-800]))
    "& h2" (create-font-styles :display-10 (gs [:colors :gray-scale-800]))
    "& h3" (create-font-styles :title-40 (gs [:colors :gray-scale-800]))
    "& h4" (create-font-styles :title-30 (gs [:colors :gray-scale-800]))
    "& h5" (create-font-styles :title-20 (gs [:colors :gray-scale-800]))
    "& h6" (create-font-styles :title-10 (gs [:colors :gray-scale-800]))

    "& p"
    (merge (create-font-styles :body-20 (gs [:colors :gray-scale-700]))
           {"& code"
            {:color (gs [:colors :gray-scale-900])
             :background-color (gs [:colors :gray-scale-25])
             :padding [[(gs [:spacing :p2])
                        (gs [:spacing :p4])
                        (gs [:spacing :p2])
                        (gs [:spacing :p4])]]}})

    "& code"
    {:font-family (gs [:font-family :monospace])

     "& *"
     {:font-family (str (gs [:font-family :monospace]) " !important")}}

    "& ul"
    {:margin [[(gs [:spacing :p20])
               (gs [:spacing :p0])
               (gs [:spacing :p20])
               (gs [:spacing :p20])]]
     :list-style :initial}

    "& ol"
    {:margin [[(gs [:spacing :p20])
               (gs [:spacing :p0])
               (gs [:spacing :p20])
               (gs [:spacing :p20])]]
     :list-style :decimal}

    "& li"
    (merge (create-font-styles :body-20 (gs [:colors :gray-scale-800]))
           {:margin-bottom (gs [:spacing :p8])

            "& code"
            {:color (gs [:colors :gray-scale-900])
             :background-color (gs [:colors :gray-scale-25])
             :padding [[(gs [:spacing :p2])
                        (gs [:spacing :p4])
                        (gs [:spacing :p2])
                        (gs [:spacing :p4])]]}})

    "& a"
    {:color (gs [:colors :link])
     :text-decoration :none}

    "& img"
    {:max-width "100%"
     :margin :auto}}

   "@keyframes spinner"
   {"0%" {:transform "rotate(0deg)"
          :strokeDashoffset (* 0.66 (gs [:constants :spinner-size]))}
    "50%" {:transform "rotate(720deg)"
           :strokeDashoffset (* 3.14 (gs [:constants :spinner-size]))}
    "100%" {:transform "rotate(1080deg)"
            :strokeDashoffset (* 0.66 (gs [:constants :spinner-size]))}}})
