(define (fibRecursive fibList fibLength)
  (begin
    (define newNum (+ (list-ref fibList (- (length fibList) 1)) (list-ref fibList (- (length fibList) 2)) ))
    (if (= fibLength (length fibList))
        
        (display fibList)

        (begin
          (fibRecursive (append fibList (List newNum)) fibLength)
          )
        )
    )    
  )

(define (fib fibLength)
  (fibRecursive (List 0 1) fibLength)

  )

; (fib desiredLength)