# pseudoRandom
## Universal Cross-Platform Multi-Language PRNG (Pseudo Random Number Generator)

The `pseudoRandom` library provides a consistent interface for generating pseudo-random numbers across multiple languages and platforms, including PHP, Python, and JavaScript. It's designed for a variety of applications, from simple simulations and games to more complex cryptographic operations.

### Features

- **Versatile**: Supports seed initialization and re-seeding for repeatable sequences.
- **Cross-platform Compatibility**: Works seamlessly in PHP, Python, and JavaScript environments.
- **Consistent Results Across Languages**: Given the same seed, it generates an identical sequence of pseudo-random numbers in all languages.
- **Flexible Seed Initialization**: Allows custom seed setting or defaults to the current epoch time in seconds if no seed is provided, ensuring unique sequences for each instance.
- **State Management**: Facilitates saving and restoring the generator's state for repeatable results.
- **Easy to Integrate**: Simplifies its inclusion into various projects, providing functionality to generate both random integers and byte strings without the need for complex setup.
- **Dieharder Test Suite Passed**: Demonstrates reliability and randomness (see dieharder.log for details)

### Installation

For **PHP**, include the library in your script:

```php
require_once('/path/to/pseudoRandom.php');
```

For **Python**, ensure the file is in your project directory or in the PYTHONPATH, and then import it:

```python
from pseudoRandom import pseudoRandom
```

For **JavaScript**, include the script in your HTML or import it in your project:

```html
<script src="path/to/pseudoRandom.js"></script>
```

### Basic Usage

#### PHP

Initialize the generator:

```php
$random = new pseudoRandom(12345);
```

Re-seed the generator:

```php
$random->reSeed("abc");
```

Generate and print a random integer:

```php
echo $random->randInt(0, 100);
```

Generate and print a random byte string:

```php
echo $random->randBytes(10);
```

Save and restore state:

```php
$random->saveState();
$random->restoreState();
```

#### Python

Initialize the generator:

```python
random = pseudoRandom()
```

Re-seed the generator:

```python
random.reSeed('abc')
```

Generate and print a random integer:

```python
print(random.randInt(0, 100))
```

Generate and print a random byte string:

```python
print(random.randBytes(10))
```

Save and restore state:

```python
random.saveState()
random.restoreState()
```

#### JavaScript

Initialize the generator:

```javascript
const random = new pseudoRandom();
```

Re-seed the generator:

```javascript
random.reSeed('abc');
```

Generate and log a random integer:

```javascript
console.log(random.randInt(0, 100));
```

Generate and log a random byte string:

```javascript
console.log(random.randBytes(10));
```

Save and restore state:

```javascript
random.saveState();
random.restoreState();
```

### How It Works

The `pseudoRandom` library utilizes a linear congruential generator (LCG), one of the oldest and simplest algorithms for generating sequences of pseudo-random numbers. The core of the LCG algorithm is a mathematical formula that produces a new number based on the previous one, using modular arithmetic. This process involves three constants—multiplier (`a`), increment (`c`), and modulus (`m`)—alongside the seed value, which initializes the sequence.

The relationship can be expressed as:

    R(n+1) = (aR(n) + c) mod m

where `R(n)` is the nth random number in the sequence. The choice of `a`, `c`, and `m` values is crucial for achieving a good distribution of pseudo-random numbers across the desired range.

`pseudoRandom` is designed to ensure that, given the same seed and the same sequence of operations, it will produce identical outcomes across all supported languages (PHP, Python, and JavaScript). This cross-platform consistency is achieved by carefully implementing the LCG algorithm with the same constants and behavior in each language version of the library.

In addition to the LCG formula, `pseudoRandom` enhances its randomness with a dynamic increment value (`c`). This value is updated using a CRC32 hash function of the current state and a counter, which increments with each number generated. This approach introduces an extra layer of unpredictability, ensuring that the sequence of numbers is even more dispersed and random.

To accommodate various use cases, the library not only allows for generating random integers within a specified range but also for producing random byte strings. Additionally, it includes functionalities for re-seeding (to start a new sequence), saving, and restoring the generator's state (to replay or backtrack the sequence of generated numbers), enhancing its versatility and applicability in projects that require deterministic random number generation.

By default, if no seed is provided, `pseudoRandom` uses the current epoch time in seconds as the seed, ensuring a unique starting point for the sequence with each new instance. Moreover, the library's reliability and randomness have been validated by passing the comprehensive Dieharder test suite. The test logs can be reviewed [here](https://github.com/toggio/pseudoRandom/blob/main/dieharder.log).

### Advanced Use

For advanced usage of the pseudoRandom library, we encourage users to review the source code, which includes self-explanatory comments that guide through more sophisticated functionalities not covered in this documentation. Exploring the source code can provide deeper insights into the library's capabilities and potential customizations.

### Contributions

Contributions to pseudoRandom are highly welcomed and appreciated. Whether it's reporting bugs, suggesting improvements, or extending the library to new languages, your input is valuable. When implementing the library in new languages, it's crucial to include all methods provided in the existing libraries and ensure that the output remains consistent across all implementations.

For example, here is how you might use the library in Python with a sample seed to generate a random number and a byte string in order to check output consistency:


```python
# Initialize the generator with a sample seed
random = pseudoRandom("a sample seed")

# Generate a random number and a byte string
print(random.randInt(0, 100000))  # Expected output: 35241
print(random.randBytes(10,0,1))    # Expected output: Os&fU%asuq

# Reinitialize seed
random.reSeed("a different seed")

# Generate a random number and a byte string
print(random.randInt(0, 100000))  # Expected output: 47786
print(random.randBytes(10,0,1))    # Expected output: xP;LOr[<+]
```

### License
**pseudoRandom** is licensed under the Apache License, Version 2.0. You are free to use, modify, and distribute the library in compliance with the license.

Copyright (C) 2024 Luca Soltoggio - https://www.lucasoltoggio.it/
