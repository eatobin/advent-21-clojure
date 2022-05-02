(ns advent.day02
  (:require [clojure.string :as str]))

; part a
(defn manipulate [[dir amt]]
  [(keyword (subs dir 0 1)) (Integer/parseInt amt)])

(def v1 (->>
          "resources/day02.txt"
          (slurp)
          (str/split-lines)
          (map #(str/split % #" "))
          (map manipulate)))
