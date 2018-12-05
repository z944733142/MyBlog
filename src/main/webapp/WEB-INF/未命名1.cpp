#include<stdio.h>
int main(){
	int n, k, i, tmp;
	scanf("%d%d", &n, &k);
	int arr[100];
	for(i = 0; i < n; i++){
	    scanf("%d", &arr[i]);
	}
	while(k--){
	    tmp = arr[n-1];
		for(i = n-1; i > 0; i--){
		    arr[i] = arr[i-1];
		}
		arr[0] = tmp;
	}
	for(i = 0; i < n; i++){
	    printf("%d ", arr[i]);
	}
	printf("\n");
	return 0;
}

