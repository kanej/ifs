(ns ifs.core
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn s-is [[x y]]
  (let [x2 (/ x 2)
        y2 (/ y 2)]
    (rand-nth [[x2 y2]
               [(+ x2 0.5) (+ y2 0.86)]
               [(+ x2 1) y2]])))

(def scale-factor 500)

(defn scale-up [[x y]]
  [(* scale-factor x) (* scale-factor y)])

(defn setup []
  ; Set frame rate to 30 frames per second.
  (q/frame-rate 10000)
  ; Set color mode to HSB (HSV) instead of default RGB.
  (q/color-mode :hsb)
  ; setup function returns initial state. It contains
  ; circle color and position.
  {:color 0
   :points [[0 0]]})

(defn update-state [state]
  (update-in state [:points 0] s-is))

(defn draw-state [state]
    (doseq [[x y] (map scale-up (:points state))]
      (q/point x y)))

(q/defsketch dejong
  :title "IFS"
  :size [1000 1000]
  ; setup function called only once, during sketch initialization.
  :setup setup
  ; update-state is called on each iteration before draw-state.
  :update update-state
  :draw draw-state
  ; This sketch uses functional-mode middleware.
  ; Check quil wiki for more info about middlewares and particularly
  ; fun-mode.
  :middleware [m/fun-mode])
