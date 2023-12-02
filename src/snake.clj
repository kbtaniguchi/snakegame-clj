(ns snake
  (:import [java.awt.event KeyEvent]
           [java.awt Color]))

(def width 75)
(def height 50)
(def point-size 10)
(def turn-millis 75)
(def win-length 5)
(def dirs {KeyEvent/VK_LEFT  [-1  0]
           KeyEvent/VK_RIGHT [1  0]
           KeyEvent/VK_UP    [0 -1]
           KeyEvent/VK_DOWN  [0  1]})

(defn add-points [& pts]
  (vec (apply map + pts)))

(defn point-to-screen-rect [pt]
  (map #(* point-size %) [(pt 0) (pt 1) 1 1]))

(defn create-apple []
  {:location [(rand-int width) (rand-int height)]
   :color (Color. 210 50 90)
   :type :apple})

(defn create-snake []
  {:body (list [1 1])
   :dir [1 0]
   :type :snake
   :color (Color. 15 160 70)})

(defn move [{:keys [body dir] :as snake} & grow]
  (assoc snake :body (cons (add-points (first body) dir)
                           (if grow body (butlast body)))))

(comment
  (add-points [1 2] [3 4] [5 6])
  (map + [1 2] [3 4] [5 6])
  (apply map + [[1 2] [3 4] [5 6]])
  (point-to-screen-rect [5 10])
  (create-apple)
  (create-snake)
  (move (create-snake))
  (move (create-snake) :grow)
  :rcf)