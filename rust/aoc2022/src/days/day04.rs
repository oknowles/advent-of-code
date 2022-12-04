fn read_input(input: &str) -> Vec<((u32,u32), (u32,u32))> {
    input.lines()
        .map(|l| {
            let pairs = l.split(',').collect::<Vec<&str>>();
            let i1 = pairs[0].split('-').map(|c| c.parse::<u32>().unwrap()).collect::<Vec<u32>>();
            let i2 = pairs[1].split('-').map(|c| c.parse::<u32>().unwrap()).collect::<Vec<u32>>();
            ((i1[0], i1[1]), (i2[0], i2[1]))
        })
        .collect()
}

fn part_one(input: Vec<((u32,u32), (u32,u32))>) -> u32 {
    input.iter()
        .filter(|(i1, i2)| completely_overlap(i1, i2))
        .count() as u32
}

fn part_two(input: Vec<((u32,u32), (u32,u32))>) -> u32 {
    input.iter()
        .filter(|(i1, i2)| partially_overlap(i1, i2))
        .count() as u32
}

fn completely_overlap(i1: &(u32, u32), i2: &(u32, u32)) -> bool {
    (i1.0 <= i2.0 && i2.1 <= i1.1) || (i2.0 <= i1.0 && i1.1 <= i2.1)
}

fn partially_overlap(i1: &(u32, u32), i2: &(u32, u32)) -> bool {
    (i1.0 <= i2.0 && i2.0 <= i1.1) || (i2.0 <= i1.0 && i1.0 <= i2.1)
}

pub fn main() {
    let input = read_input(include_str!("../../input/day04.txt"));
    let part_one = part_one(input.clone());
    let part_two = part_two(input.clone());
    println!("[Day 04] Part One: {}\tPart Two: {}", part_one, part_two);
}

mod tests {
    use crate::days::day04::{part_one, part_two, read_input};

    #[test]
    fn examples() {
        let input = read_input(include_str!("../../input/day04_example.txt"));
        let part_one_res = part_one(input.clone());
        assert_eq!(part_one_res, 2);
        let part_two_res = part_two(input.clone());
        assert_eq!(part_two_res, 4);
    }
}