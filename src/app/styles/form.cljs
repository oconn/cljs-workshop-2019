(ns app.styles.form)

(defn- base-label
  [gs]
  {:font-size (gs [:font-size :caption-20])
   :font-weight (gs [:font-weight :caption-20])
   :font-family (gs [:font-family :open-sans])
   :letter-spacing "1.1px"
   :color (gs [:colors :gray-scale-600])})

(defn- base-text
  [gs]
  {:font-size (gs [:font-size :body-20])
   :letter-spacing "1.1px"
   :font-family (gs [:font-family :open-sans])
   :color (gs [:colors :gray-scale-600])
   :font-weight (gs [:font-weight :body-20])

   (gs [:queries :tablet])
   {:font-size (gs [:font-size :body-20/tablet])}

   (gs [:queries :phone])
   {:font-size (gs [:font-size :body-20/mobile])}})

(defn- get-field-styles
  [gs]
  (merge {:width "100%"
          :height (gs [:spacing :p40])
          :padding (gs [:spacing :p4])
          :border (str "1px solid " (gs [:colors :gray-scale-50]))
          :border-radius (gs [:radius :r4])
          :background :transparent
          "&::placeholder" {:font-weight (gs [:font-weight :body-10])}
          "&:focus" {:outline :none
                     :border (str "1px solid " (gs [:colors :primary-200]))
                     :box-shadow (str "0px 0px 5px "
                                      (gs [:colors :primary-100]))}}
         (base-text gs)))

(defn- get-submit-button-styles
  [gs]
  {:display :flex
   :justify-content :center
   :appearance :none
   :align-items :center
   :text-transform :capitalize
   :padding [[(gs [:spacing :p8])
              (gs [:spacing :p20])]]
   :border-radius (gs [:radius :r4])
   :cursor :pointer
   :user-select :none
   :transition "all 120ms linear"
   :color (gs [:colors :true-white])
   :font-size (gs [:font-size :label-20])
   :font-family (gs [:font-family :open-sans])
   :border :none
   :background-color (gs [:colors :primary-500])
   "&:focus" {:border-image :none}
   "&:hover" {:background-color (gs [:colors :primary-600])}
   "&:active" {:background-color (gs [:colors :primary-400])}
   "&:disabled" {:background-color (gs [:colors :gray-scale-200])}})

(defn form
  [gs]
  (let [field-styles (get-field-styles gs)]
    {"input[type=text]" field-styles
     "input[type=date]" field-styles
     "input[type=datetime]" field-styles
     "input[type=email]" field-styles
     "input[type=number]" field-styles
     "input[type=search]" field-styles
     "input[type=time]" field-styles
     "input[type=url]" field-styles
     "input[type=password]" field-styles

     "select"
     (merge field-styles
            {:background-color (gs [:colors :true-white])
             :-webkit-appearance :none
             :text-transform :capitalize})

     "textarea"
     (merge field-styles
            {:resize :none
             :overflow :hidden
             :min-height "200px"})

     ".checkbox-form-field"
     {:display :flex
      :align-items :center
      "& label" {:order 2
                 :margin (gs [:spacing :p0])
                 :padding-top (gs [:spacing :p0])
                 :margin-left (gs [:spacing :p12])}}

     ".time-picker"
     {:display :flex
      :align-items :center}

     ".time-picker select"
     {:width "60px"}

     ".time-picker-colon"
     {:margin (str "auto " (gs [:spacing :p4]))}

     ".time-picker-period"
     {:margin-left (gs [:spacing :p4])}

     ".reagent-form-row"
     {:display :flex
      :flex-wrap :no-wrap
      :justify-content :space-between
      "& .reagent-form-field"
      {:margin-right (gs [:spacing :p16])
       "&:last-child"
       {:margin-right (gs [:spacing :p0])}}
      (gs [:queries :phone])
      {:flex-wrap :wrap
       "& .reagent-form-field"
       {:margin-right (gs [:spacing :p0])}}}

     ".reagent-form-field"
     {:margin-bottom (gs [:spacing :p4])
      :position :relative
      :width "100%"}

     ".reagent-form-field-label"
     (merge (base-label gs)
            {:font-size (gs [:font-size :caption-20])
             :display :block
             :margin-bottom (gs [:spacing :p4])
             :padding-top (gs [:spacing :p20])})

     ".reagent-form-field-error"
     (merge (base-text gs)
            {:margin-top (gs [:spacing :p0])
             :color (gs [:colors :error])
             :font-size "14px"
             :position :absolute
             :top (gs [:spacing :p0])})

     ".reagent-form-field-hint"
     (merge (base-text gs)
            {:margin-top (gs [:spacing :p0])
             :color (gs [:colors :warning])
             :font-size "14px"
             :position :absolute
             :top (gs [:spacing :p0])})

     ".reagent-form-submit-button-container"
     {:position :relative
      :padding-top (gs [:spacing :p28])
      :margin-bottom (gs [:spacing :p20])}

     ".reagent-form-errors"
     (merge (base-text gs)
            {:position :absolute
             :top (gs [:spacing :p0])
             :color (gs [:colors :error])
             :font-size "14px"
             :margin-top (gs [:spacing :p0])
             :white-space :nowrap})

     ".reagent-form-submit-button"
     (get-submit-button-styles gs)

     ".reagent-form-submitting-tag-container"
     {:position :relative
      :display :block}

     ".reagent-form-submitting-tag"
     {:position :absolute
      :top "-10px"
      :width (gs [:spacing :p20])
      :height (gs [:spacing :p20])
      :margin (str (gs [:spacing :p20] " auto"))
      :background-color "#333"
      :border-radius "100%"
      :animation "scaleout 1.0s infinite ease-in-out"}

     ".crud-form"
     {:width "100%"
      :padding (gs [:spacing :p16])
      :border (str "1px solid " (gs [:colors :primary-200]))
      :border-radius (gs [:radius :r4])}}))
