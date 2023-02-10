from bing_image_downloader import downloader
import random
numlist = [30,34,37,50,20,37,45,65,47,48,20,23,34,43,45,34,24,23,54,43,45,67,86,78]
downloader.download("rose", limit=6,  output_dir='dataset', 
                    adult_filter_off=True, force_replace=False, timeout=random.choice(numlist))
 