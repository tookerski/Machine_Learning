from numpy import *

array1 = random.rand(4,4)
print(array1)
randMat = mat(array1)
print(randMat)
invRandMat = randMat.I
print(invRandMat)
print(invRandMat*randMat)
print(invRandMat*randMat-eye(4))
print(eye(4))