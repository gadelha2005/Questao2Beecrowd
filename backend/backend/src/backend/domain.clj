(ns backend.domain)

(defn quantidade-numeros-positivos [numeros]
  (count (filter pos? numeros)))

(defn media-numeros-positivos [numeros]
  (let [positivos (filter pos? numeros)]
    (/ (reduce + 0.0 positivos)
       (count positivos))))