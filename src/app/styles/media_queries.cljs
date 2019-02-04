(ns app.styles.media-queries
  (:refer-clojure :exclude [print]))

(def desktop "@media (min-width: 801px)")
(def tablet "@media (min-width: 461px) and (max-width: 800px)")
(def tablet-below "@media (max-width: 800px)")
(def phone "@media (max-width: 460px)")
(def print "@media print")

(def queries
  {:desktop desktop
   :tablet tablet
   :tablet-below tablet-below
   :phone phone
   :print print})
