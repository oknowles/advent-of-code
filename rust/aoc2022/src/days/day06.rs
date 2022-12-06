use std::collections::{HashMap, HashSet};

fn read_input(input: &str) -> Vec<char> {
    input.lines().next().unwrap().chars().collect()
}

fn part_one(input: Vec<char>) -> usize {
    get_unique_seq(input, 4)
}

fn part_two(input: Vec<char>) -> usize {
    get_unique_seq(input, 14)
}


fn get_unique_seq(input: Vec<char>, seq_len: usize) -> usize {
    let mut chars: Vec<&char> = input.chunks(seq_len - 1).next().unwrap().iter().collect();
    for (i, c) in input.iter().enumerate().skip(seq_len - 1) {
        chars.push(c);
        let unique_chars: HashSet<&&char> = HashSet::from_iter(chars.iter());
        if unique_chars.len() == seq_len {
            return i + 1;
        }
        chars.remove(0);
    }
    panic!("No unique sequence found")
}

pub fn main() {
    let input = read_input(include_str!("../../input/day06.txt"));
    let part_one = part_one(input.clone());
    let part_two = part_two(input.clone());
    println!("[Day 06] Part One: {}\tPart Two: {}", part_one, part_two);
}

mod tests {
    use crate::days::day06::{part_one, part_two, read_input};

    #[test]
    fn examples() {
        let input = read_input(include_str!("../../input/day06_example.txt"));
        let part_one_res = part_one(input.clone());
        assert_eq!(part_one_res, 7);
        let part_two_res = part_two(input.clone());
        assert_eq!(part_two_res, 19);

        // More examples
        let example_2: Vec<char> = "bvwbjplbgvbhsrlpgdmjqwftvncz".chars().collect();
        let part_one_res = part_one(example_2.clone());
        assert_eq!(part_one_res, 5);
        let part_two_res = part_two(example_2.clone());
        assert_eq!(part_two_res, 23);

        let example_3: Vec<char> = "nppdvjthqldpwncqszvftbrmjlhg".chars().collect();
        let part_one_res = part_one(example_3.clone());
        assert_eq!(part_one_res, 6);
        let part_two_res = part_two(example_3.clone());
        assert_eq!(part_two_res, 23);

        let example_4: Vec<char> = "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg".chars().collect();
        let part_one_res = part_one(example_4.clone());
        assert_eq!(part_one_res, 10);
        let part_two_res = part_two(example_4.clone());
        assert_eq!(part_two_res, 29);

        let example_5: Vec<char> = "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw".chars().collect();
        let part_one_res = part_one(example_5.clone());
        assert_eq!(part_one_res, 11);
        let part_two_res = part_two(example_5.clone());
        assert_eq!(part_two_res, 26);
    }
}