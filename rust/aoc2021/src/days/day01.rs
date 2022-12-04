fn read_input(input: &str) -> Vec<i32> {
    input
        .lines()
        .map(|x| x.parse().expect("Not a number"))
        .collect()
}

fn part_one(input: &Vec<i32>) -> i32 {
    let mut increases: i32 = 0;
    for i in 1..input.len() {
        if input[i] > input[i - 1] {
            increases += 1;
        }
    }
    increases
}

// We don't actually need to sum each triple as there will always be two values overlapping
// We can just compare the first number of the first triple and last number of the subsequent triple
fn part_two(input: &Vec<i32>) -> usize {
    input
        .iter()
        .enumerate()
        .skip(3)
        .filter(|(i, &v)| v > input[i - 3])
        .count()
}

pub fn main() {
    let input = read_input(include_str!("../../input/day01.txt"));
    let part_one = part_one(&input);
    let part_two = part_two(&input);
    println!("[Day 01] Part One: {}\tPart Two: {}", part_one, part_two);
}

mod tests {
    use days::day01::{part_one, part_two};

    #[test]
    fn examples() {
        let input = Vec::from([199, 200, 208, 210, 200, 207, 240, 269, 260, 263]);
        let part_one_res = part_one(&input);
        assert_eq!(part_one_res, 7);
        let part_two_res = part_two(&input);
        assert_eq!(part_two_res, 5);
    }
}
