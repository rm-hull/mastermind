(ns mastermind.core
  (:require [clojure.set :refer [difference]]))

(defn calc-score [secret guess]
  (let [wrong-tokens (difference (set guess) (set secret))
        right (count
                (for [[a b] (zipmap secret guess)
                      :when (= a b)]
                  1))
        wrong (- (count secret) (count wrong-tokens) right)]
    {:right right :wrong wrong}))

(def pool [:red :green :blue :yellow :orange :white])

(defn subsequences [xs]
  (if-not (empty? xs)
    (let [[x & xs] xs]
      (cons
        (list x)
        (reduce
          (fn [r ys] (cons ys (cons (cons x ys) r)))
          []
          (subsequences xs))))))

(defn delete [x ys]
  (loop [ys  ys
         ret []]
    (cond
      (empty? ys) ret
      (= (first ys) x) (concat ret (rest ys))
      :else (recur
              (rest ys)
              (cons (first ys) ret)))))

(defn permutations [xs]
  (if (empty? xs)
    [[]]
    (for [x xs
          ys (permutations (delete x xs))]
      (cons x ys))))

(defn perms [n p]
  (for [s (subsequences p)
        :when (= (count s) n)
        s' (permutations s)]
    s'))

(def universe
  (perms 4 pool))

(defn choose-secret []
  (rand-nth universe))

(choose-secret)

(defn flip [f x y]
  (f y x))


