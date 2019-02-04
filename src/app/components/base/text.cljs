(ns app.components.base.text
  (:require [app.styles.core :refer [styles->classes
                                     gs
                                     add-class
                                     create-font-styles]]
            [app.styles.typography :refer [margin]]))

(defonce base-font-styles
  {:font-family (gs [:font-family :open-sans])
   :-webkit-text-size-adjust :none})

(def text-styles
  (reduce
   (fn [acc [font-style font-color]]
     (merge acc {font-style (create-font-styles font-style font-color)}))

   {:base base-font-styles

    :link (merge
           (create-font-styles :body-20 (gs [:colors :link]))
           base-font-styles
           {:cursor :pointer
            :text-decoration :none})

    :paragraph {:max-width "700px"
                :margin [[(gs [:spacing :p20])
                          :auto
                          (gs [:spacing :p20])
                          :auto]]

                "& p"
                {:margin-bottom (gs [:spacing :p20])}

                "& h1, h2, h3, h4, h5 ,h6"
                {:margin-bottom (gs [:spacing :p20])}}}
   [[:display-20 (gs [:colors :gray-scale-800])]
    [:title-40 (gs [:colors :gray-scale-800])]
    [:title-30 (gs [:colors :gray-scale-800])]
    [:title-20 (gs [:colors :gray-scale-800])]
    [:title-10 (gs [:colors :gray-scale-800])]
    [:body-30 (gs [:colors :gray-scale-800])]
    [:body-20 (gs [:colors :gray-scale-700])]
    [:body-10 (gs [:colors :gray-scale-600])]
    [:label-30 (gs [:colors :gray-scale-600])]
    [:label-20 (gs [:colors :gray-scale-600])]
    [:label-10 (gs [:colors :gray-scale-600])]
    [:caption-30 (gs [:colors :gray-scale-600])]
    [:caption-20 (gs [:colors :gray-scale-600])]
    [:caption-10 (gs [:colors :gray-scale-600])]]))

(def classes (styles->classes text-styles))

(defn- render-text
  [tag
   text-key
   {:keys [class] :as options}
   content]
  [tag (cond-> (merge {:class [(text-key classes)
                               (:base classes)]}
                      (dissoc options :class))
         class (update :class (if (string? class) conj into) class))
   content])

(defn- render-secondary-text
  [tag
   text-key
   {:keys [class] :as options}
   content]
  [tag (cond-> (merge {:class [(text-key classes)
                               (:secondary classes)]}
                      (dissoc options :class))
         class (update :class (if (string? class) conj into) class))
   content])

(defn display-20
  ([tag content] [display-20 tag {} content])
  ([tag options content]
   [render-text tag :display-20 options content]))

(defn display-20-alt
  ([tag content] [display-20-alt tag {} content])
  ([tag options content]
   [render-secondary-text tag :display-20 options content]))

(defn display-10
  ([tag content] [display-10 tag {} content])
  ([tag options content]
   [render-text tag :display-10 options content]))

(defn display-10-alt
  ([tag content] [display-10-alt tag {} content])
  ([tag options content]
   [render-secondary-text tag :display-10 options content]))

(defn title-40
  ([tag content] [title-40 tag {} content])
  ([tag options content]
   [render-text tag :title-40 options content]))

(defn title-40-alt
  ([tag content] [title-40-alt tag {} content])
  ([tag options content]
   [render-secondary-text tag :title-40 options content]))

(defn title-30
  ([tag content] [title-30 tag {} content])
  ([tag options content]
   [render-text tag :title-30 options content]))

(defn title-30-alt
  ([tag content] [title-30-alt tag {} content])
  ([tag options content]
   [render-secondary-text tag :title-30 options content]))

(defn title-20
  ([tag content] [title-20 tag {} content])
  ([tag options content]
   [render-text tag :title-20 options content]))

(defn title-20-alt
  ([tag content] [title-20-alt tag {} content])
  ([tag options content]
   [render-secondary-text tag :title-20 options content]))

(defn title-10
  ([tag content] [title-10 tag {} content])
  ([tag options content]
   [render-text tag :title-10 options content]))

(defn title-10-alt
  ([tag content] [title-10-alt tag {} content])
  ([tag options content]
   [render-secondary-text tag :title-10 options content]))

(defn body-30
  ([tag content] [body-30 tag {} content])
  ([tag options content]
   [render-text tag :body-30 options content]))

(defn body-30-alt
  ([tag content] [body-30-alt tag {} content])
  ([tag options content]
   [render-secondary-text tag :body-30 options content]))

(defn body-20
  ([tag content] [body-20 tag {} content])
  ([tag options content]
   [render-text tag :body-20 options content]))

(defn body-20-alt
  ([tag content] [body-20-alt tag {} content])
  ([tag options content]
   [render-secondary-text tag :body-20 options content]))

(defn body-10
  ([tag content] [body-10 tag {} content])
  ([tag options content]
   [render-text tag :body-10 options content]))

(defn body-10-alt
  ([tag content] [body-10-alt tag {} content])
  ([tag options content]
   [render-secondary-text tag :body-10 options content]))

(defn label-30
  ([tag content] [label-30 tag {} content])
  ([tag options content]
   [render-text tag :label-30 options content]))

(defn label-30-alt
  ([tag content] [label-30-alt tag {} content])
  ([tag options content]
   [render-secondary-text tag :label-30 options content]))

(defn label-20
  ([tag content] [label-20 tag {} content])
  ([tag options content]
   [render-text tag :label-20 options content]))

(defn label-20-alt
  ([tag content] [label-20-alt tag {} content])
  ([tag options content]
   [render-secondary-text tag :label-20 options content]))

(defn label-10
  ([tag content] [label-10 tag {} content])
  ([tag options content]
   [render-text tag :label-10 options content]))

(defn label-10-alt
  ([tag content] [label-10-alt tag {} content])
  ([tag options content]
   [render-secondary-text tag :label-10 options content]))

(defn caption-30
  ([tag content] [caption-30 tag {} content])
  ([tag options content]
   [render-text tag :caption-30 options content]))

(defn caption-30-alt
  ([tag content] [caption-30-alt tag {} content])
  ([tag options content]
   [render-secondary-text tag :caption-30 options content]))

(defn caption-20
  ([tag content] [caption-20 tag {} content])
  ([tag options content]
   [render-text tag :caption-20 options content]))

(defn caption-20-alt
  ([tag content] [caption-20-alt tag {} content])
  ([tag options content]
   [render-secondary-text tag :caption-20 options content]))

(defn caption-10
  ([tag content] [caption-10 tag {} content])
  ([tag options content]
   [render-text tag :caption-10 options content]))

(defn caption-10-alt
  ([tag content] [caption-10-alt tag {} content])
  ([tag options content]
   [render-secondary-text tag :caption-10 options content]))

(defn link
  [options content]
  [:a (add-class options :link classes)
   content])

(defn paragraph
  "Applies styles for a paragraph"
  [& content]
  (let [paragraph (into [:div (add-class {} :paragraph classes)]
                        content)]
    paragraph))
