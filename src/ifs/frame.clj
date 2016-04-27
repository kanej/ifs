(ns ifs.frame
  (:import (javax.swing JFrame JLabel)
           (java.awt Graphics Dimension Color)
           (java.awt.image BufferedImage)))

(defn create-panel [size]
  {:buffer (make-array Integer/TYPE size size) :size size})

(defn draw-point [x y color graphics]
  (doto graphics
    (.setColor color)
    (.drawLine x y x y)))

(defn draw [{buffer :buffer size :size} data]
  (let [image (BufferedImage. size size BufferedImage/TYPE_INT_RGB)
        canvas (proxy [JLabel] []
                 (paint [g] (.drawImage g image 0 0 this)))
        graphics (.createGraphics image)]
    ;;(paint-canvas buffer size (.createGraphics image))
    (doseq [[x y] data]
      (draw-point x y (Color. 200 200 200) graphics))
    (doto (JFrame.)
      (.add canvas)
      (.setSize (Dimension. size size))
      (.show))))
