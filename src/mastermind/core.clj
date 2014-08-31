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

(defn fix [r]     ; fix point combinator
  ((fn [f] (f f))
   (fn [f]
     (r (fn [x] ((f f) x))))))

(defn make-guess [history universe]
  (if (empty? history)
    (first universe)
    (let [[guess score] (first history)]
      (recur
        (rest history)
        (for [g' universe
              :when (= score (calc-score g' guess))]
          g')))))

(defn play-guesser [secret universe]
  (letfn [(loop [func]
            (fn [history]
              (let [guess (make-guess history universe)
                    score (calc-score secret guess)
                    history' (cons [guess score] history)]
                (if (= (score :right) 4)
                  history'
                  (func history')))))]
    ((fix loop) nil)))


(def secret (choose-secret))
(println secret)
(play-guesser secret universe)
