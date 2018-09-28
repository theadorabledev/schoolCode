#lang racket
(define (nth-prime n)
  (begin
    (define numbers (range 2 100))
    (define primes numbers)
    (for ([i numbers])
      (for ([x primes])
        (if (and (= 0 (modulo x i)) (not (= i x)))
            (let () (define primes (remove x primes)))
            (void)
            )
        ;(remove i primes)
        )
      )

    (display primes)
    )
  )

;Sieve_of_Eratosthenes
(nth-prime 10)