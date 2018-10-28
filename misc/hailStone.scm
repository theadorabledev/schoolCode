
(define (hailAdd hailList length maxNum)
  (begin
    
    (define num (car (reverse hailList)))
    (if (> num maxNum)
        (set! maxNum num)
        )
    (if (even? num)
        (begin

          (hailAdd (append hailList (List (/ num 2))) (+ length 1) maxNum)
          )
        (begin
          (if (= num 1)
              (begin
                (display hailList) (newline)
                (display (List "Length: " length)) (newline)
                (display (List "Max num: " maxNum)) (newline)
                )
              (begin
                (hailAdd (append hailList (List ( + (* num 3) 1))) (+ length 1) maxNum)
                )
              )
          )
 
        )
    )
  )

(define (hail num)
  (hailAdd (List num) 1 0)
  )


