fn read_input(input: &str) -> Vec<Vec<u32>> {
    let row_count = input.lines().count();
    let col_count = input.lines().next().unwrap().len();
    let mut grid = vec![vec![0u32; col_count]; row_count];

    input.lines().enumerate().for_each(|(row, line)| {
        line.chars().enumerate().for_each(|(col, c)| {
            let height = c.to_digit(10).unwrap();
            grid[row][col] = height;
        })
    });
    grid
}

static BASE_DIR: &str = ".";

fn part_one(input: Vec<Vec<u32>>) -> usize {
    let row_count = input.len();
    let col_count = input[0].len();
    // all trees on the edge are visible
    let mut visible_count = (row_count * 2) + (col_count * 2) - 4;
    let mut visible = vec![vec![false; col_count]; row_count];
    for row in 1..(row_count - 1) {
        visible_for_row(&input, row, &mut visible);
    }
    for col in 1..(col_count - 1) {
        visible_for_col(&input, col, &mut visible);
    }
    for row in 1..row_count - 1 {
        for col in 1..(col_count - 1) {
            if visible[row][col] {
                visible_count += 1
            }
        }
    }
    visible_count
}

fn part_two(input: Vec<Vec<u32>>) -> usize {
    let row_count = input.len();
    let col_count = input[0].len();
    let mut max_score = 0;
    for row in 1..row_count - 1 {
        for col in 1..(col_count - 1) {
            let score = scenic_score(&input, (row, col));
            if score > max_score {
                max_score = score;
            }
        }
    }
    max_score as usize
}

fn visible(heights: Vec<Vec<u32>>, tree: (usize, usize)) -> bool {
    let height = heights[tree.0][tree.1];
    println!("Check if ({},{}) with height {} is visible", tree.0, tree.1, height);
    (height > heights[tree.0][tree.1 - 1]) || (height > heights[tree.0][tree.1 + 1]) ||
        (height > heights[tree.0 - 1][tree.1]) || (height > heights[tree.0 + 1][tree.1])
}

fn visible_for_row(heights: &Vec<Vec<u32>>, row_idx: usize, visible: &mut Vec<Vec<bool>>){
    let cur_row = heights[row_idx].clone();
    let total_cols = cur_row.len();
    let mut max_height_left = cur_row[0];
    let mut max_height_right = cur_row[total_cols - 1];
    for col in 1..total_cols - 1 {
        if cur_row[col] > max_height_left {
            visible[row_idx][col] = true;
            max_height_left = cur_row[col];
        }
        if cur_row[total_cols - col - 1] > max_height_right {
            visible[row_idx][total_cols - col - 1] = true;
            max_height_right = cur_row[total_cols - col - 1];
        }
    }
}

fn visible_for_col(heights: &Vec<Vec<u32>>, col_idx: usize, visible: &mut Vec<Vec<bool>>){
    let total_rows = heights.len();
    let mut max_height_top = heights[0][col_idx];
    let mut max_height_bottom = heights[total_rows - 1][col_idx];
    for row in 1..total_rows - 1 {
        if heights[row][col_idx] > max_height_top {
            visible[row][col_idx] = true;
            max_height_top = heights[row][col_idx];
        }
        if heights[total_rows - row - 1][col_idx] > max_height_bottom {
            visible[total_rows - row - 1][col_idx] = true;
            max_height_bottom = heights[total_rows - row - 1][col_idx];
        }
    }
}


// This is not efficient - we could do better here by storing previous view counts and by knowing
// that the left view for a tree is equal to (left view of left tree + 1) if taller, or 1 if not.
fn scenic_score(heights: &Vec<Vec<u32>>, tree: (usize, usize)) -> u32 {
    let height = heights[tree.0][tree.1];
    let row_count = heights.len();
    let col_count = heights[0].len();
    let mut score = 1;

    let mut view_count = 1;
    for row in (0..tree.0).rev() {
        if heights[row][tree.1] < height {
            view_count += 1
        } else {
            if view_count > 0 {
                score *= view_count;
                view_count = 0;
            }
            break;
        }
    }
    if view_count > 0 {
        score *= view_count - 1;
        view_count = 0;
    }
    view_count = 1;
    for row in tree.0 + 1..row_count {
        if heights[row][tree.1] < height {
            view_count += 1
        } else {
            if view_count > 0 {
                score *= view_count;
                view_count = 0;
            }
            break;
        }
    }
    if view_count > 0 {
        score *= view_count - 1;
        view_count = 0;
    }
    view_count = 1;
    for col in (0..tree.1).rev() {
        if heights[tree.0][col] < height {
            view_count += 1
        } else {
            if view_count > 0 {
                score *= view_count;
                view_count = 0;
            }
            break;
        }
    }
    if view_count > 0 {
        score *= view_count - 1;
        view_count = 0;
    }
    view_count = 1;
    for col in tree.1 + 1..col_count {
        if heights[tree.0][col] < height {
            view_count += 1
        } else {
            if view_count > 0 {
                score *= view_count;
                view_count = 0;
            }
            break;
        }
    }
    if view_count > 0 {
        score *= view_count - 1;
        view_count = 0;
    }
    score
}

pub fn main() {
    let input = read_input(include_str!("../../input/day08.txt"));
    let part_one = part_one(input.clone());
    let part_two = part_two(input.clone());
    println!("[Day 08] Part One: {}\tPart Two: {}", part_one, part_two);
}

mod tests {
    use crate::days::day08::{part_one, part_two, read_input};

    #[test]
    fn examples() {
        let input = read_input(include_str!("../../input/day08_example.txt"));
        let part_one_res = part_one(input.clone());
        assert_eq!(part_one_res, 21);
        let part_two_res = part_two(input.clone());
        assert_eq!(part_two_res, 8);
    }
}