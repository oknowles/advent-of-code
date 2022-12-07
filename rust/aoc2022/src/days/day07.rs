use std::collections::HashMap;

fn read_input(input: &str) -> Vec<&str> {
    // Skip first line as it navigates to outermost directory
    input.lines().skip(1).collect()
}

static BASE_DIR: &str = ".";

fn part_one(input: Vec<&str>) -> usize {
    compute_dir_sizes(input).values().filter(|v| *v <= &100000).sum()
}

fn part_two(input: Vec<&str>) -> usize {
    let directory_sizes = compute_dir_sizes(input);
    let unused_space = 70000000 - directory_sizes.get(BASE_DIR).unwrap();
    let required_space = 30000000 - unused_space;
    let mut sorted_sizes = directory_sizes.values()
        .collect::<Vec<&usize>>();
    sorted_sizes.sort();

    for dir_size in sorted_sizes {
        if dir_size > &required_space {
            return *dir_size
        }
    }
    panic!("Sufficiently large dir not found")
}

fn compute_dir_sizes(input: Vec<&str>) -> HashMap<String, usize> {
    let mut directory_sizes = HashMap::new();
    let mut cur_dir_stack: Vec<String> = Vec::new();
    cur_dir_stack.push(String::from(BASE_DIR));

    for line in input {
        if line.starts_with("$ cd ..") {
            cur_dir_stack.pop();
        } else if line.starts_with("$ cd ") {
            let lower_dir = cur_dir_stack.last().unwrap().clone();
            let new_dir = line.split(" ").skip(2).next().unwrap();
            let thing = format!("{}/{}", lower_dir, new_dir);
            cur_dir_stack.push(thing);
        } else if !line.starts_with("$") && !line.starts_with("dir ") {
            let file_size = line.split(" ").next().unwrap().parse::<usize>().expect("Not a number");
            for dir in cur_dir_stack.clone() {
                *directory_sizes.entry(dir).or_insert(0) += file_size;
            }
        }
    }
    directory_sizes
}

pub fn main() {
    let input = read_input(include_str!("../../input/day07.txt"));
    let part_one = part_one(input.clone());
    let part_two = part_two(input.clone());
    println!("[Day 07] Part One: {}\tPart Two: {}", part_one, part_two);
}

mod tests {
    use crate::days::day07::{part_one, part_two, read_input};

    #[test]
    fn examples() {
        let input = read_input(include_str!("../../input/day07_example.txt"));
        let part_one_res = part_one(input.clone());
        assert_eq!(part_one_res, 95437);
        let part_two_res = part_two(input.clone());
        assert_eq!(part_two_res, 24933642);
    }
}