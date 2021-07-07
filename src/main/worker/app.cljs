(ns worker.app)
  
(defn ^:export init []
  (prn "I RUN IN WORKLET!"))

(defn- clojure-code []
  (merge {:a 1} {:b 2}))

(defn ^:export math [number]
  (prn "I DO EXPENSIVE CALL IN WORKLET" number)
  ((js/runOnJS js/callGlobal) (+ 10 number))
  #js {:result (+ 2 number)
       :hello (clj->js (clojure-code))})
