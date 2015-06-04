(ns ifs.core
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn s-is [[x y]]
  (let [x2 (/ x 2)
        y2 (/ y 2)]
    (rand-nth [[x2 y2]
               [(+ x2 0.5) (+ y2 0.86)]
               [(+ x2 1) y2]])))

(defn dejong-is [a b c d]
  (fn [[x y]]
    [(- (q/sin (* a y))
        (q/cos (* b x)))
     (- (q/sin (* c x))
        (q/cos (* d y)))]))

(def scale-factor 200)

(defn scale-up [[x y]]
  [(* scale-factor x) (* scale-factor y)])

(defn setup []
  ; Set frame rate to 30 frames per second.
  (q/frame-rate 30)
  ; Set color mode to HSB (HSV) instead of default RGB.
  (q/color-mode :hsb)
  ; setup function returns initial state. It contains
  ; circle color and position.
  {:color 0
   :points [[0 0]]})



(defn update-state* [ifs]
  (fn [state]
    (let [updated-points (take 1000 (iterate ifs (last (:points state))))
          updated-colour (mod (+ (:color state) 0.2) 255)]
      (assoc state :points updated-points :color updated-colour))))

(defn draw-state [state]
  (q/stroke (:color state) 255 255)
  (q/with-translation [(/ (q/width) 2)
                       (/ (q/height) 2)]
    (doseq [[x y] (map scale-up (:points state))]
      (q/point x y))))



(defn sketch-ifs [ifs]
  (q/defsketch dejong
    :title "IFS"
    :size [1000 1000]
    ; setup function called only once, during sketch initialization.
    :setup setup
    ; update-state is called on each iteration before draw-state.
    :update (update-state* ifs)
    :draw draw-state
    ; This sketch uses functional-mode middleware.
    ; Check quil wiki for more info about middlewares and particularly
    ; fun-mode.
    :middleware [m/fun-mode]))

;;(sketch-ifs (dejong-is 0.97 -1.9 1.38 -1.5))
;;(sketch-ifs (dejong-is 1.641 1.902 0.316 1.525))
;;(sketch-ifs (dejong-is 0.970 -1.899 1.381 -1.506))
(sketch-ifs (dejong-is 1.4 -2.3 2.4 -2.1))
;;(sketch-ifs (dejong-is 2.01 -2.53 1.61 -0.33))
;;(sketch-ifs (dejong-is -2.7 -0.09 -0.86 -2.2))
;;(sketch-ifs (dejong-is -2.24 0.43 -0.65 -2.43))
;;(sketch-ifs (dejong-is -2 -2 -1.2 2))
;;(sketch-ifs (dejong-is -0.709 1.638 0.452 1.740))



;;(sketch-ifs s-is)
