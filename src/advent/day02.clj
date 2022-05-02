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

(defn adder [[sh sv] [dir amt]]
  (cond
    (= dir :u) [sh (- sv amt)]
    (= dir :d) [sh (+ sv amt)]
    (= dir :f) [(+ sh amt) sv]
    :else "Whoops!"))

(def position (reduce adder [0 0] v1))

(* (first position) (last position))

;; 1989265
