### BenfordCli
CLI to apply Benford's law.

```
According to Benford's law, the frequency of the first digit in a reasonably large dataset
containing 'naturally occurring' numbers is not uniform, but logarithmic: 1 will be the leading digit in a genuine
data set of numbers 30.1% of the time; the numeral 2 will be the leading digit 17.6% of the time; and each
subsequent numeral, 3 through 9, will be the leading digit with decreasing frequency.
```

#### Usage

##### Generate cli:
```
./gradlew fatBinary
```
Binary will be generated at `build/libs/`

##### Cli Help
```
./benford --help
```


##### Example
Generate report and image from a dataset:
```
./benford --file=mean_durations.txt --experiment-name=mean_duration
```
##### Outputs
Output Console:
```
------- Mean Duration----------------------
Total numbers = 3694623

1:    1056690 -> 28.60 (30.10)  #############################
2:     642380 -> 17.39 (17.61)  #################
3:     502310 -> 13.60 (12.49)  ##############
4:     400168 -> 10.83 ( 9.69)  ###########
5:     300528 ->  8.13 ( 7.92)  ########
6:     243633 ->  6.59 ( 6.69)  #######
7:     206816 ->  5.60 ( 5.80)  ######
8:     180817 ->  4.89 ( 5.12)  #####
9:     161281 ->  4.37 ( 4.58)  ####

--- Correlation = 0.9962300643978318

```

Image Output:

![Image generated](/sample/example.png)

##### Demo
You have a binary with the last version available in `sample` folder
