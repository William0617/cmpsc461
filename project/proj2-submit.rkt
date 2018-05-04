;Q1
(define (remove-if f l)
  (if (null? l) l
      (if (f (car l)) (remove-if f (cdr l))
          (cons (car l) (remove-if f (cdr l))))))
;Q2
(define (removeLast l)
  (if (null? l) l
      (if (= (length l) 1) '()
          (cons (car l) (removeLast (cdr l))))))
;Q3
;(a)
(define (rev l1 l2)
  (if (null? l1) l2
      (append (rev (cdr l1) '()) (cons (car l1) l2))))
;(b)
(define (reverseList l)
  (if (null? l) l
       (rev l '())))
;Q4
(define (int-to-words n)
(define OneToNineteen '(one two three four five six seven eight nine ten eleven twelve thirteen fourteen fifteen sixteen seventeen eighteen nineteen))
(define TenMultipls '(twenty thirty forty fifty sixty seventy eighty ninety))
(define hundreds '(hundred ))
(cond
  ((= n 0) '(zero))
  (else
   (let loop ((n n) (units hundreds) (res '()))
     (cond
       ;n < 0,only allow 0-100 numbers
       ((< n 0) '(error))
       ;0<= n <= 100
       ((= n 0) res)
       ((< 0 n 20) (cons (list-ref OneToNineteen (- n 1)) res))
       ((< n 100) (cons (list-ref TenMultipls (- (quotient n 10) 2))
                        (loop (remainder n 10) '() res)))
       ;n > 100,only allow 0-100 numbers
       (else
        '(error)))))))
;Q5
;(a)
(define (nzero n)
  (if (= n 0) '()
      (append '(0) (nzero (- n 1)))))
;(b)
(define (polyAdd l1 l2)
  (if (null? l1) l2
      (if (null? l2) l1
          (append (cons (+ (car l1) (car l2)) '()) (polyAdd (cdr l1) (cdr l2))))))
;(c)
(define (polyAddList l)
  (if (null? l) '()
      (if (= (length l) 1) (car l)
          (if (= (length l) 2) (polyAdd (car l) (car (cdr l)))
              (polyAddList (append
                            (cons (polyAdd (car l) (car (cdr l))) '()) ;;Q????
                            (cdr (cdr l))))))))

;(d)
(define (polyMultHelper l1 l2 n)
  (if (null? l1) '()
      (cons
       (append (nzero n)(number-multiply-list l2 (car l1)))
       (polyMultHelper (cdr l1) l2 (+ 1 n))
      )))
;a number multiply a list
(define (number-multiply-list list number)
  (if (null? list)'()
      (cons (* number (car list)) (number-multiply-list (cdr list) number))))

(define (polyMult l1 l2)
  (polyAddList (polyMultHelper l1 l2 0)))
  


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;         Test Cases : Alexandar Devic          ;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;; Test Part 1
(display "Testing Part 1 : remove-if")(newline)
(if (equal? (remove-if (lambda (x) (> x 3)) '(10 1 7 2)) '(1 2)) 'pass '--fail--)
(if (equal? (remove-if (lambda (x) (equal? x '(1 2 3))) '(10 1 (1 2) 2 10 (1 2 3))) '(10 1 (1 2) 2 10)) 'pass '--fail--)
(if (equal? (remove-if (lambda (x) (equal? x '())) '(() () () 2)) '(2)) 'pass '--fail--)
(if (equal? (remove-if (lambda (x) #t) '(10 1 7 2)) '()) 'pass '--fail--)
(if (equal? (remove-if (lambda (x) #f) '(10 1 7 2)) '(10 1 7 2)) 'pass '--fail--)

;;; Test Part 2
(display "Testing Part 2 : removeLast")(newline)
(if (equal? (removeLast '(1 2 3 4)) '(1 2 3)) 'pass '--fail--)
(if (equal? (removeLast '(4)) '()) 'pass '--fail--)
(if (equal? (removeLast '((1 2 3) (1 2))) '((1 2 3))) 'pass '--fail--)
(if (equal? (removeLast '()) '()) 'pass '--fail--)
(if (equal? (removeLast '(() 12 (2 3 4) (1 2))) '(() 12 (2 3 4))) 'pass '--fail--)

;;; Test Part 3a
(display "Testing Part 3a : rev")(newline)
(if (equal? (rev '(1 2 3) '(4 5)) '(3 2 1 4 5)) 'pass '--fail--)
(if (equal? (rev '(1 2) '(4 5 6)) '(2 1 4 5 6)) 'pass '--fail--)
(if (equal? (rev '() '(4 5)) '(4 5)) 'pass '--fail--)
(if (equal? (rev '(1 2 3) '()) '(3 2 1)) 'pass '--fail--)
(if (equal? (rev '(1 (1 2 3) (4 5 6) (2) 0) '((4) 5)) '(0 (2) (4 5 6) (1 2 3) 1 (4) 5)) 'pass '--fail--)

;;; Test Part 3b
(display "Testing Part 3b : reverseList")(newline)
(if (equal? (reverseList '(1 2 3)) '(3 2 1)) 'pass '--fail--)
(if (equal? (reverseList '(4 (2) 2)) '(2 (2) 4)) 'pass '--fail--)
(if (equal? (reverseList '()) '()) 'pass '--fail--)
(if (equal? (reverseList '(1)) '(1)) 'pass '--fail--)
(if (equal? (reverseList '(1 (2 3 4) (5 6) ())) '(() (5 6) (2 3 4) 1)) 'pass '--fail--)

;;; Test Part 4
(display "Testing Part 4 : int-to-words")(newline)
(if (equal? (int-to-words 13) '(thirteen)) 'pass '--fail--)
(if (equal? (int-to-words 42) '(forty two)) 'pass '--fail--)
(if (equal? (int-to-words 0) '(zero)) 'pass '--fail--)
(if (equal? (int-to-words 10) '(ten)) 'pass '--fail--)
(if (equal? (int-to-words 30) '(thirty)) 'pass '--fail--)

;;; Test Part 5a
(display "Testing Part 5a : nzero")(newline)
(if (equal? (nzero 3) '(0 0 0)) 'pass '--fail--)
(if (equal? (nzero 10) '(0 0 0 0 0 0 0 0 0 0)) 'pass '--fail--)
(if (equal? (nzero 1) '(0)) 'pass '--fail--)
(if (equal? (nzero 5) '(0 0 0 0 0)) 'pass '--fail--)
(if (equal? (nzero 0) '()) 'pass '--fail--)

;;; Test Part 5b
(display "Testing Part 5b : polyAdd")(newline)
(if (equal? (polyAdd '(1 2 1) '(0 2 4 2)) '(1 4 5 2)) 'pass '--fail--)
(if (equal? (polyAdd '(0 2 4 2) '(1 2 1)) '(1 4 5 2)) 'pass '--fail--)
(if (equal? (polyAdd '() '(1 1 1 1)) '(1 1 1 1)) 'pass '--fail--)
(if (equal? (polyAdd '(1 1 1 1) '()) '(1 1 1 1)) 'pass '--fail--)
(if (equal? (polyAdd '(9 100 2 3 4 5 6 7 8 9) '(3 101 0 3)) '(12 201 2 6 4 5 6 7 8 9)) 'pass '--fail--)

;;; Test Part 5c
(display "Testing Part 5c : polyAddList")(newline)
(if (equal? (polyAddList '((1 2 1) (0 2 4 2))) '(1 4 5 2)) 'pass '--fail--)
(if (equal? (polyAddList '((1 2 1) (0 2 4 2) (0 0 1 2 1))) '(1 4 6 4 1)) 'pass '--fail--)
(if (equal? (polyAddList '()) '()) 'pass '--fail--)
(if (equal? (polyAddList '((1 2 3))) '(1 2 3)) 'pass '--fail--)
(if (equal? (polyAddList '((0))) '(0)) 'pass '--fail--)

;;; Test Part 5d
(display "Testing Part 5d : polyMult")(newline)
(if (equal? (polyMult '(1 2 1) '(1 2 1)) '(1 4 6 4 1)) 'pass '--fail--)
(if (equal? (polyMult '(0 1) '(1 2 1 7)) '(0 1 2 1 7)) 'pass '--fail--)
(if (equal? (polyMult '(3) '(1 2 1 7)) '(3 6 3 21)) 'pass '--fail--)
(if (equal? (polyMult '(0) '(3 56 2 7 3 7 1 7 2)) '(0 0 0 0 0 0 0 0 0)) 'pass '--fail--)
(if (equal? (polyMult '(1 0 1 4 9 1) '(3 1 0 0 0 1)) '(3 1 3 13 31 13 1 1 4 9 1)) 'pass '--fail--)