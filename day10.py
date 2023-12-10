import cv2 as cv

image = cv.imread("./input.png")

if image is None:
    exit("could not read the image.")

gray = cv.cvtColor(image, cv.COLOR_BGR2GRAY)
thresh = cv.threshold(gray, 127, 255, cv.THRESH_BINARY)[1]

cnts = cv.findContours(thresh, cv.RETR_LIST, cv.CHAIN_APPROX_SIMPLE)[-2] 

xcnts = []
for cnt in cnts: 
    if 0 < cv.contourArea(cnt) < 2: 
        xcnts.append(cnt)
        ((x, y), r) = cv.minEnclosingCircle(cnt)
        cv.circle(image, (int(x), int(y)), int(r), (0, 255, 0), 3)

print(len(xcnts))

# cv.imwrite('result.png', image)

cv.imshow('thresh', image)
cv.waitKey()
