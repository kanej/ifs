(ns dev
  "Tools for interactive development with the REPL. This file should
  not be included in a production build of the application."
  (:require
   [clojure.java.io :as io]
   [clojure.java.javadoc :refer [javadoc]]
   [clojure.pprint :refer [pprint]]
   [clojure.reflect :refer [reflect]]
   [clojure.repl :refer [apropos dir doc find-doc pst source]]
   [clojure.set :as set]
   [clojure.string :as str]
   [clojure.test :as test]
   [clojure.tools.namespace.repl :refer [refresh refresh-all]]
   [clojure.java.io :as io]
   [clojure.core.matrix :as mat]
   [ifs.frame :as f]
   [ifs.systems :as s]
   [ifs.matrix :as m])
  (:import (java.awt.image BufferedImage)
           (javax.imageio ImageIO)
           (java.awt Color)
           (java.io File)))

(defn dejong [iterations]
  (let [ifs (s/dejong-is 0.97 -1.9 1.38 -1.5)]
    (take iterations (iterate ifs [0 0]))))

(defn dd []
  (f/draw (f/create-panel 1024) (dejong 10000)))


(defn build-matrix []
  (->> (dejong 10000)
       (map (partial f/scale 200))
       (map (partial f/transform 512 512))
       (populate-matrix (mat/new-matrix 1024 1024))))

;;(populate-matrix (mat/new-matrix 10 10) (dejong 100))


;; De Jong

;;(sketch-ifs (dejong-is 0.97 -1.9 1.38 -1.5))
;;(sketch-ifs (dejong-is 1.641 1.902 0.316 1.525))
;;(sketch-ifs (dejong-is 0.970 -1.899 1.381 -1.506))
;;(sketch-ifs (dejong-is 1.4 -2.3 2.4 -2.1))
;;(sketch-ifs (dejong-is 2.01 -2.53 1.61 -0.33))
;;(sketch-ifs (dejong-is -2.7 -0.09 -0.86 -2.2))
;;(sketch-ifs (dejong-is -2.24 0.43 -0.65 -2.43))
;;(sketch-ifs (dejong-is -2 -2 -1.2 2))
;;(sketch-ifs (dejong-is -0.709 1.638 0.452 1.740))

;; Clifford
;;(sketch-ifs (clifford-is -1.4 1.6 1.0 0.7))
;;(sketch-ifs (clifford-is 1.6 -0.6 -1.2 1.6))
;;(sketch-ifs (clifford-is 1.7 1.7 0.6 1.2))
;;;(sketch-ifs (clifford-is 1.5 -1.8 1.6 0.9))
;;(sketch-ifs (clifford-is -1.7 1.3 -0.1 -1.2))
;;(sketch-ifs (clifford-is -1.7 1.8 -1.9 -0.4))
;;(sketch-ifs (clifford-is -1.8 -2.0 -0.5 -0.9))

;; Me
;;(sketch-ifs (clifford-is -1.945 2.0 -0.5 -0.9))

;;(sketch-ifs s-is)
