(ns ifs.core
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(def scale-factor 200)

(defn scale-up [[x y]]
  [(* scale-factor x) (* scale-factor y)])

(defn setup []
  ; Set frame rate to 30 frames per second.
  (q/frame-rate 30)
  ; Set color mode to HSB (HSV) instead of default RGB.
  (q/color-mode :hsb)
  (q/background 255)
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
  ;;(q/smooth 0)
  (q/stroke (:color state) 255 255 100)
  (q/with-translation [(/ (q/width) 2)
                       (/ (q/height) 2)]
    (doseq [[x y] (map scale-up (:points state))]
      (q/point x y))))

(defn sketch-ifs [ifs]
  (q/defsketch dejong
    :title "IFS"
    :size [1024 1024]
    ; setup function called only once, during sketch initialization.
    :setup setup
    ; update-state is called on each iteration before draw-state.
    :update (update-state* ifs)
    :draw draw-state
    ; This sketch uses functional-mode middleware.
    ; Check quil wiki for more info about middlewares and particularly
    ; fun-mode.
    :middleware [m/fun-mode]))
