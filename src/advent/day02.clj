(ns advent.day02
  (:require [clojure.string :as str]))

; part a
(def v1 (->>
          "resources/day02.txt"
          (slurp)
          (str/split-lines)
          (map #(str/split % #" "))
          (map #(Integer/parseInt (% 1)))
          (into [])))
