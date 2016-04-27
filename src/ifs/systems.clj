(ns ifs.systems)

(defn s-is [[x y]]
  (let [x2 (/ x 2)
        y2 (/ y 2)]
    (rand-nth [[x2 y2]
               [(+ x2 0.5) (+ y2 0.86)]
               [(+ x2 1) y2]])))

(defn dejong-is [a b c d]
  (fn [[x y]]
    [(- (Math/sin (* a y))
        (Math/cos (* b x)))
     (- (Math/sin (* c x))
        (Math/cos (* d y)))]))

(defn clifford-is [a b c d]
  (fn [[x y]]
    [(+ (Math/sin (* a y)) (* c (Math/cos (* a x))))
     (+ (Math/sin (* b x)) (* d (Math/cos (* b y))))]))

