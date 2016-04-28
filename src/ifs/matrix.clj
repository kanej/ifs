(ns ifs.matrix
  (:require [clojure.core.matrix :as mat]))

(defn init-system [ifs size]
  (let [underlying-matrix (mat/new-matrix size size)]
    {:matrix underlying-matrix :size size :seed [0 0] :ifs ifs}))

(defn- point->co-ordinate [[x y]]
  [(int x) (int y)])

(defn- update-matrix [matrix [x y :as point]]
  (if (or (< x 0) (>= x 1024) (< y 0) (>= y 1024))
    matrix
    (update-in matrix (point->co-ordinate point) inc)))

(defn- populate-matrix [matrix points]
  (loop [pts points m matrix]
    (if (empty? pts)
      m
      (recur (rest pts) (update-matrix m (first pts))))))

(defn perform-iterations [{ifs :ifs seed :seed matrix :matrix} no-of-iterations]
  (let [iterations (take iterations (iterate ifs [0 0]))]))
