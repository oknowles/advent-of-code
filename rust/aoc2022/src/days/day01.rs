fn read_input(input: &str) -> Vec<Vec<i32>> {
    let mut init: Vec<Vec<i32>> = Vec::new();
    init.push(vec![]);
    for line in input.lines() {
        if line.is_empty() {
            init.push(vec![]);
        } else {
            let i = init.len();
            let thing = &mut init[i - 1];
            thing.push(line.parse::<i32>().expect("Not a number"));
        }
    }
    init
}

fn part_one(input: Vec<Vec<i32>>) -> i32 {
    input
        .iter()
        .max_by_key(|x| x.iter().sum::<i32>())
        .unwrap()
        .iter()
        .sum()
}

fn part_two(input: Vec<Vec<i32>>) -> i32 {
    let mut top_three = vec![0; 3];
    input
        .iter()
        .map(|x| x.iter().sum::<i32>())
        .for_each(|s| {
            if s > top_three[0] {
                top_three[2] = top_three[1];
                top_three[1] = top_three[0];
                top_three[0] = s;
            } else if s > top_three[1] {
                top_three[2] = top_three[1];
                top_three[1] = s;
            } else if s > top_three[2] {
                top_three[2] = s;
            }
        });
    top_three.iter().sum()
}

pub fn main() {
    let input = read_input(include_str!("../../input/day01.txt"));
    let part_one = part_one(input.clone());
    let part_two = part_two(input.clone());
    println!("[Day 01] Part One: {}\tPart Two: {}", part_one, part_two);
}

mod tests {
    use crate::days::day01::{part_one, part_two, read_input};

    #[test]
    fn examples() {
        let input = read_input(include_str!("../../input/day01_example.txt"));
        let part_one_res = part_one(input.clone());
        assert_eq!(part_one_res, 24000);
        let part_two_res = part_two(input.clone());
        assert_eq!(part_two_res, 45000);
    }
}