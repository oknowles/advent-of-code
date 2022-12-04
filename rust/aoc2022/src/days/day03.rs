use std::collections::HashSet;
use std::io::ErrorKind::ConnectionReset;
use std::ops::IndexMut;

fn read_input(input: &str) -> Vec<&str> {
    input.lines().collect()
}

fn part_one(input: Vec<&str>) -> i32 {
    input.iter()
        .map(|i| i.split_at(i.len() / 2))
        .map(|i| to_value(common_char(vec![i.0, i.1])))
        .sum()
}

fn part_two(input: Vec<&str>) -> i32 {
    input.chunks(3)
        .map(|group| to_value(common_char(group.to_vec())))
        .sum()
}

fn common_char(strings: Vec<&str>) -> char {
    let mut char_sets = vec![HashSet::new(); strings.len() - 1];
    for i in 0..char_sets.len() {
        char_sets[i] = strings[i].chars().collect::<HashSet<char>>();
    }
    for c in strings.last().unwrap().chars().into_iter() {
        let mut common = true;
        for char_set in &char_sets {
            if !char_set.contains(&c) {
                common = false;
                break;
            }
        }
        if common {
            return c
        }
    }
    panic!("No common char found")
}

fn to_value(c: char) -> i32 {
    if 'a' <= c && c <= 'z' {
        c as i32 - 'a' as i32 + 1
    } else {
        c as i32 - 'A' as i32 + 27
    }
}

pub fn main() {
    let input = read_input(include_str!("../../input/day03.txt"));
    let part_one = part_one(input.clone());
    let part_two = part_two(input.clone());
    println!("[Day 03] Part One: {}\tPart Two: {}", part_one, part_two);
}

mod tests {
    use crate::days::day03::{part_one, part_two, read_input};

    #[test]
    fn examples() {
        let input = read_input(include_str!("../../input/day03_example.txt"));
        let part_one_res = part_one(input.clone());
        assert_eq!(part_one_res, 157);
        let part_two_res = part_two(input.clone());
        assert_eq!(part_two_res, 70);
    }
}