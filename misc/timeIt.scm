#lang racket
(define (fibRecursive fibList fibLength)
  (begin
    (define newNum (+ (list-ref fibList (- (length fibList) 1)) (list-ref fibList (- (length fibList) 2)) ))
    (if (= fibLength (length fibList))
        
        (display fibList)

        (begin
          (fibRecursive (append fibList (list newNum)) fibLength)
          )
        )
    )    
  )

(define (fib fibLength)
  (fibRecursive (list 0 1) fibLength)

  )

(define (recursive-call times func args)
  (if (= times 1)
      (apply func args)
      (begin
        (recursive-call (- times 1) func args)
        (apply func args)
        )
      )
  )

;(recursive-call 5 fib '(10))

(define (timeIt func args)
  (begin
    
    (define startTime (current-milliseconds))

    ;(apply func args) (newline)
    (recursive-call 100000 func args)
    (define nowTime (current-milliseconds))


    (define elapsedTime (/ (- nowTime startTime) 1000))
    (newline)
    (display "Times you can run in one second: " )
    (display (round (/ 1(/(/ (- nowTime startTime) 1000) 100000)))) ; get the time in milliseconds, turn it into seconds, divide by number of operations performed, turn it into a whole number
    )
  )

;Use: (timeIt function-name '(args for function) )
;Example: (timeIt fib '(12))   (timeIt + '(5 7))

