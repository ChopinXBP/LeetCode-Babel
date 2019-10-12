/**
 *
 * An image is represented by a 2-D array of integers, each integer representing the pixel value of the image (from 0 to 65535).
 * Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel value newColor, "flood fill" the image.
 * To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel,
 * plus any pixels connected 4-directionally to those pixels (also with the same color as the starting pixel), and so on.
 * Replace the color of all of the aforementioned pixels with the newColor.
 * At the end, return the modified image.
 * 有一幅以二维整数数组表示的图画，每一个整数表示该图画的像素值大小，数值在 0 到 65535 之间。
 * 给你一个坐标 (sr, sc) 表示图像渲染开始的像素值（行 ，列）和一个新的颜色值 newColor，让你重新上色这幅图像。
 * 为了完成上色工作，从初始坐标开始，记录初始坐标的上下左右四个方向上像素值与初始坐标相同的相连像素点，
 * 接着再记录这四个方向上符合条件的像素点与他们对应四个方向上像素值与初始坐标相同的相连像素点，……，重复该过程。将所有有记录的像素点的颜色值改为新的颜色值。
 * 最后返回经过上色渲染后的图像。
 *
 */

public class FloodFill {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        Solution(image, sr, sc, image[sr][sc]);
        for(int i = 0; i < image.length; i++){
            for(int j = 0; j < image[0].length; j++){
                image[i][j] = image[i][j] == -1 ? newColor : image[i][j];
            }
        }
        return image;
    }

    public void Solution(int[][] images, int x, int y, int srcColor){
        if(x < 0 || y < 0 || x >= images.length || y >= images[0].length || images[x][y] != srcColor || images[x][y] == -1){
            return;
        }
        images[x][y] = -1;
        Solution(images, x - 1, y, srcColor);
        Solution(images, x + 1, y, srcColor);
        Solution(images, x, y + 1, srcColor);
        Solution(images, x, y - 1, srcColor);
    }
}
