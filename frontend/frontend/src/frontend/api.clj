(ns frontend.api
  (:require [clj-http.client :as http]
            [cheshire.core :refer [parse-string]]
            [clojure.string :as str]))

(def backend-url "http://localhost:3007")

(defn get-positivos [numeros]
  (let [params (str/join "," numeros)
        body (:body (http/get (str backend-url "/positivos")
                              {:query-params {"numeros" params}
                               :as :text}))]
    (parse-string body true)))

