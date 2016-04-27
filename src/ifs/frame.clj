(ns ifs.frame
  (:import (javax.swing JFrame JLabel)
           (java.awt Graphics Dimension Color)
           (java.awt.image BufferedImage)))

(defn draw-point [x y color graphics]
  (doto graphics
    (.setColor color)
    (.drawLine x y x y)))

(defn create-panel [size]
  (let [image (BufferedImage. size size BufferedImage/TYPE_INT_RGB)
        canvas (proxy [JLabel] []
                 (paint [g] (.drawImage g image 0 0 this)))
        graphics (.createGraphics image)
        frame (JFrame.)]    
    (doto frame
      (.add canvas)
      (.setSize (Dimension. size size))
      (.show))
    {:frame frame :canvas canvas :graphics graphics :size size}))


(defn scale [factor [x y]]
  [(* factor x) (* factor y)])

(defn transform [x-move y-move [x y]]
  [(+ x-move x) (+ y-move y)])

(defn draw [{frame :frame canvas :canvas graphics :graphics} data]
  (do
    (doseq [point data]
      (let [[x y] (->> point (scale 200) (transform 512 512))]
        (draw-point x y (Color. 200 200 200) graphics)))
    (.repaint canvas)
    (.show frame)))
