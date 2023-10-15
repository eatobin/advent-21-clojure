(ns advent.day01
  (:require [clojure.string :as str]))

; part a
;(def v1 (->>
;          "resources/day01.txt"
;          (slurp)
;          (str/split-lines)
;          (map #(Integer/parseInt %))
;          (into [])))

(def slurped-split (str/split-lines
                    (slurp "resources/day01.txt")))

(def xform
  (map #(Integer/parseInt %)))

(def v1 (transduce xform conj slurped-split))

(def v2 (into [] (rest v1)))

(reduce + (map #(if % 1 0) (map < v1 v2)))

;; 1529

;part b
(def gp1 (into [] (partition 3 1 v1)))

(def gp2 (into [] (rest gp1)))

(def t1 (map #(reduce + %) gp1))

(def t2 (map #(reduce + %) gp2))

(reduce + (map #(if % 1 0) (map < t1 t2)))

;; 1567


;; These are examples of how to view a transducer

;; (https://stackoverflow.com/questions/26317325/can-someone-explain-clojure-transducers-to-me-in-simple-terms)

;; https://clojure.org/reference/transducers#_transduce

(reduce + (filter odd? (map #(+ 2 %) (range 0 10))))

(def xform1
  (comp
   (partial filter odd?)
   (partial map #(+ 2 %))))
(reduce + (xform1 (range 0 10)))

(defn xform2 [xs]
  (->> xs
       (map #(+ 2 %))
       (filter odd?)))
(reduce + (xform2 (range 0 10)))

(def xform3
  (comp
   (map #(+ 2 %))
   (filter odd?)))
(transduce xform3 + (range 0 10))

;; (transduce xform f coll)
;; (transduce xform f init coll)

(def xf (comp (filter odd?) (map inc)))
(transduce xf + (range 5))
;; => 6
(transduce xf + 100 (range 5))
;; => 106

;; The composed xf transducer will be invoked left-to-right with a final call to the reducing function f.
;; In the last example, input values will be filtered, then incremented, and finally summed.
