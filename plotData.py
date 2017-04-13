# plotData.py
# Plots data that I don't know how to plot in Java
# Aviva Blonder

import matplotlib.pyplot as plt

def plot():
    softmax = [-9.248395030888172, -9.378838868502623, -9.48163248768753, -9.612820382770328, -9.353358617950768, -8.583820461029891, -9.313165144840656, -8.161526714836276, -6.672433261152457, -9.531197713597694, -8.124198260214543, -9.497197502063548, -6.13229872786175, -8.77011329334065, -6.358636028468842, -6.930537025564348, -6.961231655308705, -8.511336946022645, -4.8753114731322675, -5.808481700849948, -2.6413106557335144, -5.318508642724795, -6.706815695564352, -1.7811069623524403, -5.223455405290067, -3.607169444700791, -5.808481700849948, -6.209265983177302, -4.823546942557845, -6.502120297989417, -5.680178688064141, -5.223455405290067, -5.2712208512371665, -4.1010495227209605, -1.9446629338016266, -5.766143132171665, -4.1010495227209605, -3.7970429050237327, -2.416060055440142, -3.607169444700791, -4.977292774816936, -3.3426966395054762, -3.071940056764442, -3.7970429050237327, -5.457553617527226, -3.671097750253783, -4.718443977714362, -2.4918994548857407, -4.218438637218813, -4.218438637218813, -1.443965046609386, -2.9312723770680975, -3.411483928872225, -2.4918994548857407, -2.4918994548857407, -1.6980878407600408, -2.416060055440142, -1.7811069623524403, -1.7811069623524403, -6.13229872786175, -1.443965046609386, -2.2620753550047366, -2.416060055440142, -4.501792671108807, -2.0252163044636102, -3.277710365138481, -2.0252163044636102, -1.7811069623524403, -2.4918994548857407, -2.2620753550047366, -1.8632958927289156, -2.2620753550047366, -1.6142301421818597, -2.6413106557335144, -1.7811069623524403, -1.6980878407600408, -1.8632958927289156, -2.104964141418974, -2.1839145000047844, -2.3394546014546895, -1.6980878407600408, -1.5295253961432926, -1.6142301421818597, -2.8574714472071006, -1.2702428799197896, -1.3575404511205922, -1.5295253961432926, -1.3575404511205922, -1.9446629338016266, -1.443965046609386, -2.0252163044636102, -1.2702428799197896, -1.1820635150704946, -1.9446629338016266, -1.6980878407600408, -1.0929934495661562, -1.8632958927289156, -1.2702428799197896, -1.1820635150704946, -1.2702428799197896]
    egreedy1 = [-5.807073023471855, -9.979825367603564, -9.85535364670968, -8.540961340796795, -7.412617078355485, -9.36622678145355, -9.927700396385612, -5.678726890857204, -7.752221920791363, -7.411747515698218, -9.545121610306127, -5.502978081351953, -6.247173323345528, -5.502978081351953, -7.8623854122892505, -6.898490349872336, -6.9295054463736125, -7.021703145368061, -5.547948300538434, -5.63654412935772, -6.899532349054897, -4.1010495227209605, -3.734386772751245, -8.732040283861588, -3.8590724759734956, -3.2098084496348296, -5.808481700849948, -3.7970429050237327, -5.502978081351953, -7.251792378957017, -3.7970429050237327, -3.5425953986876677, -3.4773690895835028, -2.8598710879475733, -2.4918994548857407, -3.277710365138481, -5.7233769011835, -3.071940056764442, -3.92048175121376, -3.4773690895835028, -3.411483928872225, -3.671097750253783, -2.0252163044636102, -3.8590724759734956, -2.4918994548857407, -3.4773690895835028, -1.8632958927289156, -2.787748573684418, -2.4918994548857407, -3.7970429050237327, -1.6980878407600408, -3.411483928872225, -1.9446629338016266, -2.787748573684418, -5.5924688175330495, -3.92048175121376, -1.6980878407600408, -2.0252163044636102, -1.6980878407600408, -3.2098084496348296, -3.671097750253783, -1.8632958927289156, -3.671097750253783, -2.2620753550047366, -2.9312723770680975, -3.1412206561967975, -2.7148975491761793, -1.2702428799197896, -2.416060055440142, -2.0252163044636102, -2.416060055440142, -1.443965046609386, -2.787748573684418, -1.5295253961432926, -1.7811069623524403, -3.071940056764442, -2.6413106557335144, -2.416060055440142, -1.0030236864304607, -1.2702428799197896, -1.9446629338016266, -2.1839145000047844, -3.0019596532974164, -1.0929934495661562, -1.6142301421818597, -1.0929934495661562, -1.8632958927289156, -2.0252163044636102, -1.0929934495661562, -1.9446629338016266, -1.6980878407600408, -1.7811069623524403, -2.0252163044636102, -1.9446629338016266, -1.2702428799197896, -1.8632958927289156, -1.5295253961432926, -1.7811069623524403, -1.8632958927289156, -1.2702428799197896]
    egreedy5 = [-9.99930402150537, -4.499944844204211, -7.683417091835983, -7.966466600194111, -8.510836639017382, -8.434091543713619, -9.957128760968535, -7.987478298600326, -8.666695076071784, -6.502120297989417, -9.168929825257194, -6.4310991715023125, -8.124198260214543, -5.547948300538434, -7.840793345746718, -8.693227844057954, -8.40283403162639, -2.6413106557335144, -5.316935296147237, -6.502120297989417, -9.554173690261036, -5.457553617527226, -6.83658029696449, -2.104964141418974, -5.077244648598079, -5.027519847068767, -3.92048175121376, -3.7970429050237327, -8.511336946022645, -7.8623854122892505, -6.803552667654338, -4.218438637218813, -5.5924688175330495, -7.539424586234875, -4.276254250846625, -6.245912080601012, -1.8632958927289156, -5.932973985853004, -2.0252163044636102, -4.276254250846625, -3.4773690895835028, -5.932973985853004, -5.7233769011835, -5.126472202112098, -3.411483928872225, -3.8590724759734956, -6.013907803534529, -4.276254250846625, -4.5567747443977185, -3.277710365138481, -5.678726890857204, -4.76950227293701, -2.2620753550047366, -8.198112333211032, -4.218438637218813, -5.973644245994474, -4.5567747443977185, -2.8598710879475733, -2.787748573684418, -2.7148975491761793, -4.041464164364607, -3.7970429050237327, -2.787748573684418, -3.1412206561967975, -4.501792671108807, -1.6142301421818597, -3.344933261487096, -2.4918994548857407, -4.975604753069223, -3.4773690895835028, -3.0019596532974164, -2.7148975491761793, -7.223099764288135, -2.787748573684418, -2.9312723770680975, -2.4918994548857407, -3.7970429050237327, -4.3901567912547765, -2.416060055440142, -2.9312723770680975, -3.4773690895835028, -3.344933261487096, -1.9446629338016266, -2.4918994548857407, -5.808481700849948, -5.124834316298314, -2.3394546014546895, -2.4918994548857407, -2.104964141418974, -2.787748573684418, -3.0019596532974164, -3.4773690895835028, -2.9312723770680975, -4.718443977714362, -4.8753114731322675, -2.787748573684418, -6.053768725499184, -3.5425953986876677, -2.416060055440142, -4.041464164364607]

    x = range(100)
    plt.plot(x, softmax, "r", x, egreedy1, "b", x, egreedy5, "g")
    plt.legend(["Softmax", "Epsilon-greedy (.1)", "Epsilon-greedy (.5)"], loc = 4)
    plt.xlabel("Episode")
    plt.ylabel("Discounted Cumulative Reward")
    plt.show()

plot()
